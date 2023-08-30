package br.com.hogwarts.artifactsonline.system;

import br.com.hogwarts.artifactsonline.artifact.Artifact;
import br.com.hogwarts.artifactsonline.artifact.ArtifactRepository;
import br.com.hogwarts.artifactsonline.wizard.Wizard;
import br.com.hogwarts.artifactsonline.wizard.WizardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DBDataInitializer implements CommandLineRunner {

    private final ArtifactRepository artifactRepository;
    private final WizardRepository wizardRepository;
    @Override
    public void run(String... args) throws Exception {
        Artifact a1 = Artifact.builder()
                .id("1250808601744904191")
                .name("Deluminator")
                .description("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.")
                .imageUrl("ImageUrl")
                .build();

        Artifact a2 = Artifact.builder()
                .id("1250808601744904192")
                .name("Invisibility Cloak")
                .description("An invisibility cloak is used to make the wearer invisible.")
                .imageUrl("ImageUrl")
                .build();

        Artifact a3 = Artifact.builder()
                .id("1250808601744904193")
                .name("Elder Wand")
                .description("The Elder Wand, known throughout history as the Deathstick or the Wand of Destiny, is an extremely powerful wand made of elder wood with a core of Thestral tail hair.")
                .imageUrl("ImageUrl")
                .build();

        Artifact a4 = Artifact.builder()
                .id("1250808601744904194")
                .name("The Marauder's Map")
                .description("A magical map of Hogwarts created by Remus Lupin, Peter Pettigrew, Sirius Black, and James Potter while they were students at Hogwarts.")
                .imageUrl("ImageUrl")
                .build();

        Artifact a5 = Artifact.builder()
                .id("1250808601744904195")
                .name("The Sword Of Gryffindor")
                .description("A goblin-made sword adorned with large rubies on the pommel. It was once owned by Godric Gryffindor, one of the medieval founders of Hogwarts.")
                .imageUrl("ImageUrl")
                .build();

        Artifact a6 = Artifact.builder()
                .id("1250808601744904196")
                .name("Resurrection Stone")
                .description("The Resurrection Stone allows the holder to bring back deceased loved ones, in a semi-physical form, and communicate with them.")
                .imageUrl("ImageUrl")
                .build();

        Wizard w1 = Wizard.builder()
                .id(1)
                .name("Albus Dumbledore")
                .addArtifact(a1)
                .addArtifact(a3)
                .build();

        Wizard w2 = Wizard.builder()
                .id(2)
                .name("Harry Potter")
                .addArtifact(a2)
                .addArtifact(a4)
                .build();

        Wizard w3 = Wizard.builder()
                .id(3)
                .name("Neville Longbottom")
                .addArtifact(a5)
                .build();

        wizardRepository.save(w1);
        wizardRepository.save(w2);
        wizardRepository.save(w3);

        artifactRepository.save(a6);

    }
}
