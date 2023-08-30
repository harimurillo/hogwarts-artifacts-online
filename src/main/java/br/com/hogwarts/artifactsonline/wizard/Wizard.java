package br.com.hogwarts.artifactsonline.wizard;

import br.com.hogwarts.artifactsonline.artifact.Artifact;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Wizard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner")
    @Singular private List<Artifact> artifacts = new ArrayList<>();

    public Integer getNumberOfArtifacts() {
        return this.artifacts.size();
    }

    public static class WizardBuilder {
        public WizardBuilder addArtifact(Artifact artifact) {
            artifact.setOwner(this.build());
            return artifact(artifact);
        }
    }
}
