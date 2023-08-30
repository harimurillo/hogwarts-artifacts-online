package br.com.hogwarts.artifactsonline.wizard.dto;

import lombok.Builder;

@Builder
public record WizardDto(Integer id,
                        String name,
                        Integer numberOfArtifacts) {
}
