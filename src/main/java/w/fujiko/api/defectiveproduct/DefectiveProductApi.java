package w.fujiko.api.defectiveproduct;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fte.api.Api;
import w.fujiko.dto.defectiveproduct.DefectiveProductDefaultDTO;
import w.fujiko.dto.destinations.DestinationDTO;
import w.fujiko.model.masters.defectiveproduct.DefectiveProduct;
import w.fujiko.service.defectiveproduct.DefectiveProductService;

@RestController
@RequestMapping("/api/defective-product")
public class DefectiveProductApi extends Api<DefectiveProductService, DefectiveProduct, Integer>{
	private ModelMapper modelMapper;

	public DefectiveProductApi() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setAmbiguityIgnored(false);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<DefectiveProductDefaultDTO> getAll(){
		List<DefectiveProduct> defective = service.get();
		Type listType = new TypeToken<List<DefectiveProductDefaultDTO>>() {}.getType();
		List<DefectiveProductDefaultDTO> defectiveProductDTO = this.modelMapper.map(defective, listType);
		return defectiveProductDTO;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id){
		Optional<DefectiveProduct> response = service.get(id);
		if(!response.isPresent()){
			return ResponseEntity.notFound().build();
		}
		DefectiveProduct defectiveProduct = response.get();
		DefectiveProductDefaultDTO defectiveProductDefaultDTO = this.modelMapper.map(defectiveProduct,DefectiveProductDefaultDTO.class);
		return ResponseEntity.ok(defectiveProductDefaultDTO);
	}
}
