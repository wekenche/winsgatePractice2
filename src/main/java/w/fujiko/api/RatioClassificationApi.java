package w.fujiko.api;

import static w.fujiko.util.common.constants.ProductConstants.MSG_500;

import java.lang.reflect.Type;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.dto.ratioclassifications.RatioClassificationDTO;
import w.fujiko.model.masters.RatioClassification;
import w.fujiko.service.RatioClassificationService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/ratio-classification")
public class RatioClassificationApi
	extends Api<RatioClassificationService, RatioClassification, Integer> {
	
	private ModelMapper modelMapper;
	
	public RatioClassificationApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}
	
	@GetMapping("/{makerId}/code-search")
	public ResponseEntity<?> getUndeletedItemByCode(
			@RequestParam(value = "code", defaultValue = "") String code,
			@PathVariable(value="makerId") String makerId) {
		
		try {
			
			int cd = Integer.parseInt(code);
			int id = Integer.parseInt(makerId);
			Optional<RatioClassification> result = service.getUndeletedItemByCode(cd, id);
			
			if(result.isPresent()) {
				Type type = new TypeToken<RatioClassificationDTO>() {}.getType();
				RatioClassificationDTO ratioClassificationDTO = this.modelMapper.map(result.get(), type);
				return ResponseEntity.ok().body(ratioClassificationDTO);
			} else {
				return ResponseEntity.ok().body(null);
			}
			
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}

}