package br.com.hogwarts.artifactsonline.artifact.converter;

import br.com.hogwarts.artifactsonline.artifact.Artifact;
import br.com.hogwarts.artifactsonline.artifact.dto.ArtifactDto;
import br.com.hogwarts.artifactsonline.wizard.converter.WizardToWizardDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArtifactToArtifactDtoConverter implements Converter<Artifact, ArtifactDto> {

    private final WizardToWizardDtoConverter wizardToWizardDtoConverter;
    @Override
    public ArtifactDto convert(Artifact source) {
        return ArtifactDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .imageUrl(source.getImageUrl())
                .owner(source.getOwner() != null
                        ? this.wizardToWizardDtoConverter.convert(source.getOwner())
                        : null)
                .build();
    }
}
