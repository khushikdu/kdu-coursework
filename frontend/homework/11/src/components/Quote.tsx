import './Quote.scss'
import { ApiQuote } from "../types/quotetypes";

interface QuoteProps{
    quote: ApiQuote
    onSelectTag:(tag:string)=>void;
}

export function Quote({quote,onSelectTag}:QuoteProps){

    const handleTagClick=(tag:string)=>{
        onSelectTag(tag)
    };

    return(
        <div className="quote-container">
            <h1>{quote.content}</h1>
            <p>~{quote.author}</p>
            <p>{quote.dateAdded}</p>

            <ul className="tags">
                {
                    quote.tags.map((tag)=>{
                        return <button key={tag} onClick={()=>handleTagClick(tag)}>{tag}</button>
                    })
                }
            </ul>
        </div>
    )
}