package br.com.hogwarts.artifactsonline.artifact;

import br.com.hogwarts.artifactsonline.artifact.converter.ArtifactDtoToArtifactConverter;
import br.com.hogwarts.artifactsonline.artifact.converter.ArtifactToArtifactDtoConverter;
import br.com.hogwarts.artifactsonline.artifact.dto.ArtifactDto;
import br.com.hogwarts.artifactsonline.system.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/artifacts")
public class ArtifactController {

    private final ArtifactService artifactService;
    private final ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter;
    private final ArtifactDtoToArtifactConverter artifactDtoToArtifactConverter;
    @GetMapping("/{artifactId}")
    public Result findArtifactById(@PathVariable String artifactId) {
        Artifact foundArtifact = artifactService.findById(artifactId);
        ArtifactDto artifactDto = artifactToArtifactDtoConverter.convert(foundArtifact);
        return new Result(true, HttpStatus.OK.value(), "Find One Success", artifactDto);
    }
    @GetMapping
    public Result findAllArtifactById() {
        List<Artifact> foundArtifacts = this.artifactService.findAll();
        List<Object> artifactDtos = foundArtifacts.stream()
                .map(this.artifactToArtifactDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, HttpStatus.OK.value(), "Find All Success", artifactDtos);
    }
    @PostMapping
    public Result addArtifact(@Valid @RequestBody ArtifactDto artifactDto) {
        Artifact newArtifact = this.artifactDtoToArtifactConverter.convert(artifactDto);
        Artifact savedArtifact = this.artifactService.save(newArtifact);
        ArtifactDto savedArtifactDto = this.artifactToArtifactDtoConverter.convert(savedArtifact);
        return new Result(true, HttpStatus.OK.value(), "Add Success", savedArtifactDto);
    }
    @PutMapping("/{artifactId}")
    public Result updateArtifact(@PathVariable String artifactId,
                                 @Valid @RequestBody ArtifactDto artifactDto) {
        Artifact update = this.artifactDtoToArtifactConverter.convert(artifactDto);
        Artifact updatedArtifact = this.artifactService.update(artifactId, update);
        ArtifactDto updatedArtifactDto = this.artifactToArtifactDtoConverter.convert(updatedArtifact);
        return new Result(true, HttpStatus.OK.value(), "Update Success", updatedArtifactDto);
    }
    @DeleteMapping("/{artifactId}")
    public Result deleteArtifact(@PathVariable String artifactId) {
        this.artifactService.delete(artifactId);
        return new Result(true, HttpStatus.OK.value(), "Delete Success");
    }
}
