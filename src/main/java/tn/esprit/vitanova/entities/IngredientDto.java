package tn.esprit.vitanova.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

    private Long IdIngredients;
    private String description;
    private String products;
    private String images;
}