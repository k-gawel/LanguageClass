package org.tutor.materials.model.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record TextbookInput(@NotBlank @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters.") String name) {
}
