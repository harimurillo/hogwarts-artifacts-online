package br.com.hogwarts.artifactsonline.artifact.dto;

import br.com.hogwarts.artifactsonline.wizard.dto.WizardDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record ArtifactDto(String id,
                          @NotEmpty(message = "name is required.")
                          String name,
                          @NotEmpty(message = "description is required.")
                          String description,
                          @NotEmpty(message = "imageUrl is required.")
                          String imageUrl,
                          WizardDto owner) {
}
