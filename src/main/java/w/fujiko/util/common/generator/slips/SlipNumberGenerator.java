package w.fujiko.util.common.generator.slips;

import w.fujiko.exceptions.SlipNumberGenerationException;

public interface SlipNumberGenerator {
    String generate() throws SlipNumberGenerationException;
    String generate(Integer userId) throws SlipNumberGenerationException;
}