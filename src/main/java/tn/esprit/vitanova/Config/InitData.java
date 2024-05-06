package tn.esprit.vitanova.Config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.vitanova.entities.*;
import tn.esprit.vitanova.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
public class InitData {
    @Autowired
    private DishTypeRepository dishTypeRepository;
    @Autowired
    private RecepiesRepository recepiesRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;


    public InitData(DishTypeRepository dishTypeRepository, RecepiesRepository recepiesRepository, IngredientsRepository ingredientsRepository) {
        this.dishTypeRepository = dishTypeRepository;
        this.recepiesRepository = recepiesRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @PostConstruct
    public void initDishTypes() {
        // Vérifier si la table est vide avant d'insérer les données pour éviter les doublons
        if (dishTypeRepository.count() == 0) {
            // Créer les types de plats
            DishType appetizer = new DishType("Entrée");
            DishType mainCourse = new DishType("Plat principal");
            DishType dessert = new DishType("Dessert");
            DishType snack = new DishType("Collation");
            DishType beverage = new DishType("Boisson");

            // Enregistrer les types de plats dans la base de données
            dishTypeRepository.saveAll(Arrays.asList(appetizer, mainCourse, dessert, snack, beverage));
        }
    }

    @PostConstruct
    public void initIngredients() {
        // Vérifier si la table est vide avant d'insérer les données pour éviter les doublons
        if (ingredientsRepository.count() == 0) {
            List<Object[]> ingredientsData = List.of(

                    new Object[]{"Tomato", "Fresh red tomatoes", "https://i5.walmartimages.com/seo/Fresh-Slicing-Tomato-Each_a1e8e44a-2b82-48ab-9c09-b68420f6954c.04f6e0e87807fc5457f57e3ec0770061.jpeg?odnHeight=640&odnWidth=640&odnBg=FFFFFF"},
                    new Object[]{"Basil", "Organic green basil leaves", "https://eu.clickandgrow.com/cdn/shop/products/Basil_plant_1280x960_506752fc-b241-4f68-9cd0-2cdb579b051e.jpg"},
                    new Object[]{"Garlic", "Natural white garlic", "https://media-cldnry.s-nbcnews.com/image/upload/t_fit-1240w,f_auto,q_auto:best/rockcms/2023-10/garlic-today-main-190906-0a67d6.jpg"},
                    // Ajoutez les autres données d'ingrédients ici
                    new Object[]{"Pasta", "Italian pasta - spaghetti", "https://lepidor.com.tn/wp-content/uploads/spaghetti-1.png"},
                    new Object[]{"Olive Oil", "Extra virgin olive oil", "https://www.topoliva.com/98-medium_default/la-medina-1-l.jpg"},
                    new Object[]{"Salt", "Pure Himalayan salt", "https://www.realsimple.com/thmb/oIaigAKlBvf6vQKC0D0sDodaAaE=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/does-salt-expire-GettyImages-1478475170-6ce3516215ac41c18d37a05333f8c0ca.jpg"},
                    new Object[]{"Pomme", "Les pommes sont des fruits provenant des pommiers, des arbres provenant de la famille des Rosaceae", "https://www.conservation-nature.fr/wp-content/uploads/visuel/fruit/shutterstock_575378506.jpg"},
                    new Object[]{"Citron", "Le citronnier (Citrus limon L. Burm pp.) appartient à la famille des Rutacea. Les principaux cultivars sont Femminello Santa Teresa, ", "https://atablelepiceriefine.com/wp-content/uploads/2023/01/Citron.jpg"},
                    new Object[]{"Banane", "Ce fruit jaune et allongé est souvent mangé tel quel, en ne retirant que la pelure. La banane est aussi à la base de plusieurs recettes servies au déjeuner, en collation ou au dessert. Dans un pain sucré, un muffin ou un biscuit, la banane a toujours sa p", "https://fondationolo.ca/wp-content/uploads/2015/09/banana-1024x741.jpg"},
                    new Object[]{"Fruits secs", "Il existe deux familles de fruits secs : les oléagineux ou fruits à coque, qui sont naturellement secs (amandes, cacahuètes, noisettes, noix…)", "https://www.lespaniersdelea.com/wp-content/uploads/2016/01/fruitssecs-1080x608.jpg"},
                    new Object[]{"Oeuf", "C’est le Concile de Nicée en 352 qui plaça la fête de Pâques à la période qui suit l’équinoxe. De Charlemagne à Louis XVI, le Carême interdisait les œufs au même titre que la viande.", "https://www.framboizeinthekitchen.com/wp-content/uploads/2018/04/oeufs.jpg"},
                    new Object[]{"Avocados", "Creamy, succulent avocados not only contain the best kind of fat (monounsaturated oleic acid) but also help your body block the absorption of bad fats (cholesterol). They're high in lutein, which aids eyesight, and in potassium and folate, which may reduc", "https://www.oprah.com/g/image-resizer?width=670&link=https://static.oprah.com/images/201001/omag/201001-omag-food-avocado-284x426.jpg"},
                    new Object[]{"Cruciferous Vegetables", "Whether orange or white, sweet potatoes contain phytonutrients that promote heart and eye health and boost immunity. They're flush with beta-carotene (thought to lower breast cancer risk) and vitamin A (which may reduce the effects of smoking).", "https://www.oprah.com/g/image-resizer?width=670&link=https://static.oprah.com/images/201001/omag/201001-omag-food-cabbage-284x426.jpg"},
                    new Object[]{"Alliums", "Dark, leafy greens such as spinach, kale, and swiss chard are an excellent source of iron (especially important for women), vitamin A, and lutein for eye health. Best of all, you know those omega-3s everyone's talking about? They reside in dark greens (in", "https://www.oprah.com/g/image-resizer?width=670&link=https://static.oprah.com/images/201001/omag/201001-omag-food-leeks-284x426.jpg"},
                    new Object[]{"Spelt", "Don't eat whole grains (such as buckwheat and quinoa) just because they are high in magnesium, B vitamins, fiber, and manganese. Do it because they taste great—nutty, buttery, earthy. And that, in turn, may actually help you to not overeat—one study found", "https://www.oprah.com/g/image-resizer?width=670&link=https://static.oprah.com/images/201001/omag/201001-omag-food-spelt-284x426.jpg"}
            );

            // Boucle à travers les données d'ingrédients et les sauvegarder dans le repository
            for (Object[] data : ingredientsData) {
                Ingredients ingredient = new Ingredients();
                ingredient.setProducts((String) data[0]);
                ingredient.setDescription((String) data[1]);
                ingredient.setImages((String) data[2]);

                // Sauvegarder l'ingrédient dans le repository
                ingredientsRepository.save(ingredient);
            }
        }


    }


    @PostConstruct
    public void initRecipes() {
        // Vérifier si la table est vide avant d'insérer les données pour éviter les doublons
        if (recepiesRepository.count() == 0) {
            List<Object[]> recipesData = List.of(
                    new Object[]{"2024-04-26 09:32:54.000000", "Start your day with a burst of tropical flavours that's sure to put you in a good mood.- Australian Passionfruit", 30, "https://img.bestrecipes.com.au/UXUvx1ns/w643-h428-cfill-q90/br/2018/07/passionfruit-acai-smoothie-bowl-recipe-523091-1.jpg", "Passionfruit Acai Smoothie Bowl", 5},
                    new Object[]{"2024-04-26 00:02:44.000000", "Eating the rainbow is a sure-fire way to ensure you get a wide range of nutrients and minerals. This bowl has all my favourite vegies: curry roasted cauliflower, sesame carrot chips and sautéed greens. Get creative with your vegies, feel free to mix and match with this recipe. Enjoy!- Jess Sepel", 50, "https://img.bestrecipes.com.au/5JTuV8Fk/w643-h428-cfill-q90/br/2019/05/1980-health-rainbow-vegie-bowl-diabetic-friendly-952242-1.jpg", "Healthy Rainbow Vegie Bowl", 5},
                    new Object[]{"2024-04-26 09:34:56.000000", "There are a lot of different options and combinations here. Ground turkey is lower in fat and can easily become dry when cooking. Then there’s ground pork, chicken, or lean ground beef. For these meatballs, I use a blend of regular ground beef and ground Italian sausage. It has enough fat to keep them moist and flavorful and it’s higher in iron and omega-3 fatty acids.", 60, "https://wellnessmama.com/wp-content/uploads/Grain-free_Italian_Meatballs_in_Zucchini_Marinara.jpg", "Gluten-Free Meatballs in Zucchini Marinara", 5},
                    new Object[]{"2024-04-26 00:02:44.000000", "Plant-based dining is Australia’s latest food trend, and these fritters are the perfect way to get a taste of the action. Kick-start your day with a balanced breakfast, and an unexpected zesty twist.- Roza's Gourmet", 60, "https://img.bestrecipes.com.au/RiCejbq2/w643-h428-cfill-q90/br/2018/09/zesty-vegan-breakfast-fritters-recipe-523678-1.jpg", "Zesty Vegan Breakfast Fritters", 1},
                    new Object[]{"2024-04-26 09:34:56.000000", "This is one of the easiest recipes you’ll ever make! While the ingredients list may seem a little long, it really pulls together fast.", 45, "https://wellnessmama.com/wp-content/uploads/cabbage-stir-fry.jpg", "Beef and Cabbage Stir-Fry", 5},
                    new Object[]{"2024-04-26 09:34:56.000000", "This recipe is gluten-free, grain-free, dairy-free, and low-carb. If you tolerate dairy, try them with sour cream or topped with cheese. It makes enough for 4 adult servings, but kids usually don’t eat a whole pepper.", 20, "https://wellnessmama.com/wp-content/uploads/4Stuffed_Bell_Peppers.jpg", "Stuffed Bell Peppers Recipe (With a Tex-Mex Twist!)", 1},
                    new Object[]{"2024-04-26 09:32:54.000000", "Making chocolate at home involves melting cocoa butter, cocoa powder, honey, and vanilla on the stove. I use a double boiler (or heat-safe bowl set over a pan with water). Next, you’ll pour the chocolate recipe into molds for it to set.", 30, "https://wellnessmama.com/wp-content/uploads/chocolate-recipe.jpg", "Healthy Homemade Chocolate Recipe", 3},
                    new Object[]{"2024-04-26 09:34:56.000000", "Unlike other cookies that have a base of flour, sugar, and butter, these rely on foamy egg whites instead. If you’ve ever had the fluffy dessert pavlova or lemon meringue pie, then you’ll recognize the texture", 40, "https://wellnessmama.com/wp-content/uploads/Cocoa_Meringue_Cookies.jpg", "Chocolate Meringue Cookies", 3},
                    new Object[]{"2024-04-26 09:40:40.000000", "Fondue comes from the French word fondre, which means to melt. Traditionally it’s made with a variety of melted cheeses to make a cheese fondue.", 45, "https://wellnessmama.com/wp-content/uploads/New_Years_Chocolate_Fondue_3.jpg", "Dark Chocolate Fondue Recipe", 3},
                    new Object[]{"2024-04-26 09:32:54.000000", "You may have already heard of them, but in case you haven’t mocktails are non-alcoholic cocktails. ", 10, "https://wellnessmama.com/wp-content/uploads/herbal-mocktails-.png", "Refreshing Herbal Mocktails", 4},
                    new Object[]{"2024-04-26 00:02:44.000000", "Coconut water is one of the simplest sports drink alternatives and can be used as is. It’s similar in structure to the fluid used in IV rehydration.", 5, "https://wellnessmama.com/wp-content/uploads/Natural_Electrolyte_Drink_Recipe_3.jpg", "Homemade Electrolyte Drink (With Flavor Options)", 3}

            );
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");

            for (Object[] data : recipesData) {
                Recepies recipe = new Recepies();
                try {
                    Date dateAdded = dateFormat.parse((String) data[0]);
                    recipe.setDateAdded(dateAdded);
                    recipe.setDescription((String) data[1]);
                    recipe.setDuration((int) data[2]);
                    recipe.setImages((String) data[3]);
                    recipe.setName((String) data[4]);
                    // Convertir Integer en Long
                    Long dishTypeId = ((Integer) data[5]).longValue();

// Utiliser dishTypeId pour rechercher le DishType
                    DishType dishType = dishTypeRepository.findById(dishTypeId)
                            .orElseThrow(() -> new IllegalArgumentException("Type de plat non trouvé avec l'identifiant: " + dishTypeId));

                    recipe.setDishType(dishType);

                } catch (ParseException e) {
                    // Gérer l'erreur de format de date
                    e.printStackTrace();
                }
                recepiesRepository.save(recipe);

            }
        }

    }

//    @Bean
//    CommandLineRunner usersINIT(UserRepository repository) {
//        return args -> {
//            if (repository.count() == 0) {
//                log.info("populating users table");
//                List<User> users = new ArrayList<>();
//                User u1 = new User();
//                u1.setNom("Test");
//                u1.setEmail("test@vita.com");
//                u1.setPassword("mypassword");
//                u1.setGender("female");
//                u1.setPrenom("User");
//
//
//                User u2 = new User();
//                u2.setNom("Test");
//                u2.setEmail("test2@vita.com");
//                u2.setPassword("mypassword");
//                u2.setGender("female");
//                u2.setPrenom("User2");
//
//                users.add(u1);
//                users.add(u2);
//                repository.saveAll(users);
//            }
//        };
//    }

    @Bean
    CommandLineRunner achievementsINIT(AchievementRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Achievement> achievementsList = new ArrayList<>();
                log.info("populating achievements table");
                for (AchievementSlug slug : AchievementSlug.values()) {
                    if (slug.equals(AchievementSlug.ADD_10_COMMENT)) {
                        Achievement a = new Achievement();
                        a.setCriteria(10);
                        a.setSlug(slug);
                        a.setPublicName("Add 10 comments");
                        achievementsList.add(a);
                    } else if (slug.equals(AchievementSlug.SUBSCRIBE)) {
                        Achievement a = new Achievement();
                        a.setCriteria(5);
                        a.setSlug(slug);
                        a.setPublicName("Subscribe");
                        achievementsList.add(a);
                    } else {
                        Achievement a = new Achievement();
                        a.setCriteria(15);
                        a.setSlug(slug);
                        a.setPublicName("Add 15 comments");
                        achievementsList.add(a);
                    }
                }
                repository.saveAll(achievementsList);
            }
        };
    }

    @Bean
    CommandLineRunner rolesInit(RoleRepo repository) {
        return args -> {
            if (repository.count() == 0) {
                List<ERole> roles = Arrays.stream(ERole.values()).toList();
                for (ERole eRole : roles) {
                    repository.save(new Role(eRole));
                }

            }
        };
    }
}