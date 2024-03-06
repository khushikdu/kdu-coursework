import { Category } from "./category";
import { Rating } from "./Rating";

export interface ProductDetailsType {
    id:          number;
    title:       string;
    price:       number;
    description: string;
    category:    Category;
    image:       string;
    rating:      Rating;
}

    