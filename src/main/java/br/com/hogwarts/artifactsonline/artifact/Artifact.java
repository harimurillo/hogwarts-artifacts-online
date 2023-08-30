package br.com.hogwarts.artifactsonline.artifact;

import br.com.hogwarts.artifactsonline.wizard.Wizard;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Artifact implements Serializable {
    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    @ManyToOne
    private Wizard owner;
}
