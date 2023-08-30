package br.com.hogwarts.artifactsonline.artifact;

import br.com.hogwarts.artifactsonline.artifact.dto.ArtifactDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ArtifactControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ArtifactService artifactService;
    @Autowired
    ObjectMapper objectMapper;
    List<Artifact> artifacts;

    @BeforeEach
    void setUp() {
        artifacts = new ArrayList<>();

        Artifact a1 = Artifact.builder()
                .id("1250808601744904191")
                .name("Deluminator")
                .description("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.")
                .imageUrl("ImageUrl")
                .build();
        artifacts.add(a1);

        Artifact a2 = Artifact.builder()
                .id("1250808601744904192")
                .name("Invisibility Cloak")
                .description("An invisibility cloak is used to make the wearer invisible.")
                .imageUrl("ImageUrl")
                .build();
        artifacts.add(a2);

        Artifact a3 = Artifact.builder()
                .id("1250808601744904193")
                .name("Elder Wand")
                .description("The Elder Wand, known throughout history as the Deathstick or the Wand of Destiny, is an extremely powerful wand made of elder wood with a core of Thestral tail hair.")
                .imageUrl("ImageUrl")
                .build();
        artifacts.add(a3);

        Artifact a4 = Artifact.builder()
                .id("1250808601744904194")
                .name("The Marauder's Map")
                .description("A magical map of Hogwarts created by Remus Lupin, Peter Pettigrew, Sirius Black, and James Potter while they were students at Hogwarts.")
                .imageUrl("ImageUrl")
                .build();
        artifacts.add(a4);

        Artifact a5 = Artifact.builder()
                .id("1250808601744904195")
                .name("The Sword Of Gryffindor")
                .description("A goblin-made sword adorned with large rubies on the pommel. It was once owned by Godric Gryffindor, one of the medieval founders of Hogwarts.")
                .imageUrl("ImageUrl")
                .build();
        artifacts.add(a5);

        Artifact a6 = Artifact.builder()
                .id("1250808601744904196")
                .name("Resurrection Stone")
                .description("The Resurrection Stone allows the holder to bring back deceased loved ones, in a semi-physical form, and communicate with them.")
                .imageUrl("ImageUrl")
                .build();
        artifacts.add(a6);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindArtifactByIdSuccess() throws Exception {
        // Given
        given(this.artifactService.findById("1250808601744904191")).willReturn(this.artifacts.get(0));

        // When and then
        mockMvc.perform(get("/api/v1/artifacts/1250808601744904191").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value("1250808601744904191"))
                .andExpect(jsonPath("$.data.name").value("Deluminator"));
    }

    @Test
    void testFindArtifactByIdNotFound() throws Exception {
        // Given
        given(this.artifactService.findById("1250808601744904191")).willThrow(new ArtifactNotFoundException("1250808601744904191"));

        // When and then
        mockMvc.perform(get("/api/v1/artifacts/1250808601744904191").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value("Could not find artifact with id 1250808601744904191 :("))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testFindAllArtifactsSuccess() throws Exception {
        // Given
        given(this.artifactService.findAll()).willReturn(this.artifacts);

        // When and Then
        this.mockMvc.perform(get("/api/v1/artifacts").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.artifacts.size())))
                .andExpect(jsonPath("$.data[0].id").value("1250808601744904191"))
                .andExpect(jsonPath("$.data[0].name").value("Deluminator"))
                .andExpect(jsonPath("$.data[1].id").value("1250808601744904192"))
                .andExpect(jsonPath("$.data[1].name").value("Invisibility Cloak"));
    }

    @Test
    void testAddArtifactSuccess() throws Exception {
        // Given
        ArtifactDto artifactDto = ArtifactDto.builder()
                .name("Remembrall")
                .description("A Remembrall was a magical large marble-sized glass ball that contained smoke which turned red when its owner or user had forgotten something. It turned clear once whatever was forgotten was remembered.")
                .imageUrl("ImageUrl")
                .build();

        String json = this.objectMapper.writeValueAsString(artifactDto);

        Artifact savedArtifact = Artifact.builder()
                .id("1250808601744904197")
                .name("Remembrall")
                .description("A Remembrall was a magical large marble-sized glass ball that contained smoke which turned red when its owner or user had forgotten something. It turned clear once whatever was forgotten was remembered.")
                .imageUrl("ImageUrl")
                .build();

        given(this.artifactService.save(Mockito.any(Artifact.class))).willReturn(savedArtifact);

        // When and Then
        this.mockMvc.perform(post("/api/v1/artifacts").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value(savedArtifact.getName()))
                .andExpect(jsonPath("$.data.description").value(savedArtifact.getDescription()))
                .andExpect(jsonPath("$.data.imageUrl").value(savedArtifact.getImageUrl()));

    }

    @Test
    void testUpdateArtifactSuccess() throws Exception {
        // Given
        ArtifactDto artifactDto = ArtifactDto.builder()
                .id("1250808601744904192")
                .name("Invisibility Cloak")
                .description("A new discription.")
                .imageUrl("ImageUrl")
                .owner(null)
                .build();

        String json = this.objectMapper.writeValueAsString(artifactDto);

        Artifact updatedArtifact = Artifact.builder()
                .id("1250808601744904192")
                .name("Invisibility Cloak")
                .description("A new discription.")
                .imageUrl("ImageUrl")
                .build();

        given(this.artifactService.update(eq("1250808601744904192"), Mockito.any(Artifact.class))).willReturn(updatedArtifact);

        // When and Then
        this.mockMvc.perform(put("/api/v1/artifacts/1250808601744904192").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id").value("1250808601744904192"))
                .andExpect(jsonPath("$.data.name").value(updatedArtifact.getName()))
                .andExpect(jsonPath("$.data.description").value(updatedArtifact.getDescription()))
                .andExpect(jsonPath("$.data.imageUrl").value(updatedArtifact.getImageUrl()));
    }

    @Test
    void testUpdateArtifactErrorWithNonExisttentId() throws Exception {
        // Given
        ArtifactDto artifactDto = ArtifactDto.builder()
                .id("1250808601744904192")
                .name("Invisibility Cloak")
                .description("A new discription.")
                .imageUrl("ImageUrl")
                .owner(null)
                .build();

        String json = this.objectMapper.writeValueAsString(artifactDto);

        given(this.artifactService.update(eq("1250808601744904192"), Mockito.any(Artifact.class))).willThrow(new ArtifactNotFoundException("1250808601744904192"));

        // When and Then
        this.mockMvc.perform(put("/api/v1/artifacts/1250808601744904192").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value("Could not find artifact with id 1250808601744904192 :("))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDeleteArtifactSuccess() throws Exception {
        // Given
        doNothing().when(this.artifactService).delete("1250808601744904191");

        // When and then
        this.mockMvc.perform(delete("/api/v1/artifacts/1250808601744904191").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Delete Success"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDeleteArtifactErrorWithNonExistentId() throws Exception {
        // Given
        doThrow(new ArtifactNotFoundException("1250808601744904191")).when(this.artifactService).delete("1250808601744904191");

        // When and then
        this.mockMvc.perform(delete("/api/v1/artifacts/1250808601744904191").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value("Could not find artifact with id 1250808601744904191 :("))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}