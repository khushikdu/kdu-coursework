interface IRecipe {
    image: string,
    name: string,
    rating: number,
    cuisine: string,
    ingredients: string[],
    difficulty: string,
    timeTaken: number,
    calorieCount: number
}

// Define the interface for the RecipeSearchApp class
interface IRecipeSearchApp {
    fetchRecipesFromAPI(): Promise<JSON>;
    searchRecipes(query: string): Promise<JSON>;
    printAllRecipes(recipesArr: IRecipe[]): void;
}

// Search for recipes based on a query
class RecipeSearchApp implements IRecipeSearchApp{
    async fetchRecipesFromAPI(): Promise<JSON>  {
        try{
            const response = await fetch('https://dummyjson.com/recipes');
            const body = await response.json();
            return body;
        }catch(error) {
            console.error("Error fetching recipes:", error);
            throw error;
        }
    }

    // Transform API response into IRecipe format
    async searchRecipes(query: string): Promise<JSON> {
        try {
            const response = await fetch(`https://dummyjson.com/recipes/search?q=${query}`);
            const body = await response.json();
            const recipesArray: any[] = body.recipes;
            const recipes: IRecipe[] = [];
            recipesArray.forEach(recipe => {
                const responseObject = {
                    image: recipe.image,
                    name: recipe.name,
                    rating: recipe.rating,
                    cuisine: recipe.cuisine,
                    ingredients: recipe.ingredients,
                    difficulty: recipe.difficulty,
                    timeTaken: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
                    calorieCount: recipe.caloriesPerServing
                };
                recipes.push(responseObject);
            });
            this.printAllRecipes(recipes);
            return body;

        }
        catch (error) {
            console.error("Could not find recipe:", error);
            throw error;
        }
    }

    // Print all recipes
    printAllRecipes(recipesArr: IRecipe[]): void { 
        recipesArr.forEach(recipe => console.log(recipe));
    }
}

const recipes = new RecipeSearchApp();
console.log(recipes.searchRecipes("Aloo Keema"));