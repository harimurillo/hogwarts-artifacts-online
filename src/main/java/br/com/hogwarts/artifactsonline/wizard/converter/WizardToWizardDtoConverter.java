package br.com.hogwarts.artifactsonline.wizard.converter;

import br.com.hogwarts.artifactsonline.wizard.Wizard;
import br.com.hogwarts.artifactsonline.wizard.dto.WizardDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WizardToWizardDtoConverter implements Converter<Wizard, WizardDto> {
    @Override
    public WizardDto convert(Wizard source) {
        return WizardDto.builder()
                .id(source.getId())
                .name(source.getName())
                .numberOfArtifacts(source.getNumberOfArtifacts())
                .build();
    }
}
