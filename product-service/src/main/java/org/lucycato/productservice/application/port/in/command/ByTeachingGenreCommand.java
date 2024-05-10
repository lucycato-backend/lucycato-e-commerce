package org.lucycato.productservice.application.port.in.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productservice.domain.enums.TeachingGenre;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ByTeachingGenreCommand extends SelfValidating<ByIdsCommand> {
    @NotEmpty
    private List<TeachingGenre> teachingGenres;

    public ByTeachingGenreCommand(List<TeachingGenre> teachingGenres) {
        this.teachingGenres = teachingGenres;

        this.validateSelf();
    }
}
