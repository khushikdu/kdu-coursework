import { ProductDetailsType } from "../types/ProductDetailsType"

interface Products{
    individual_product:ProductDetailsType
}

export function ProductList({individual_product}:Products){
    return (
        <div className="product-list">
            Product List Page
            <div className="indiv-product">
                <img src={individual_product.image} alt="prod-img"/>
                <h1>{individual_product.title} - ${individual_product.title}</h1>
            </div>
        </div>
    )
}