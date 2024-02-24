import { useRef } from "react";
import "./ScrollToTop.scss";
export function ScrollToTop() {
  const paragraphs: JSX.Element[] = [];
  for (let i = 0; i < 10; i++) {
    paragraphs.push(
      <p key={i}>
        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Optio libero
        nulla, ex nostrum veniam consequuntur voluptates, quibusdam officiis
        reiciendis culpa vitae, modi amet ratione exercitationem aperiam
        assumenda possimus ipsam quidem similique consectetur illum commodi
        adipisci blanditiis minus! Optio ad reiciendis assumenda deserunt
        consequuntur inventore quasi molestiae quis, fugiat nihil nulla sint
        iste ipsum architecto, obcaecati maiores. Doloremque nemo, neque ex,
        doloribus ut eaque beatae unde quisquam hic soluta accusantium fugit
        consectetur consequatur labore nobis, esse voluptatum excepturi
        repudiandae itaque magni. Aliquam hic, totam vel rem odio veritatis
        dolore explicabo harum quod tempora! Debitis velit quod accusantium
        necessitatibus repellendus molestiae repudiandae? Lorem, ipsum dolor sit
        amet consectetur adipisicing elit. Facere, similique ipsum! Eaque eum
        inventore sed facilis suscipit ipsum cum perspiciatis odio quibusdam.
        Expedita excepturi, quia cupiditate sit error in quidem at. Nihil,
        fugit. Officia iure maiores facere. Quisquam ratione, explicabo quas
        sint aliquam corporis cum quaerat, quos hic esse porro provident
        perferendis modi nulla quo blanditiis voluptates id, recusandae maxime
        qui nam expedita. Minus beatae natus libero pariatur quo in deleniti aut
        laboriosam facere, unde nobis suscipit ipsum voluptates tempore,
        recusandae consequuntur enim sint commodi placeat! In modi illo, sed
        neque repudiandae deleniti. In sed animi voluptatibus culpa quia odio.
      </p>
    );
  }
  return (
    <div className="text-para">
      <h1>Scroll to top using useRef</h1>
      <p id="body">{paragraphs}</p>
    </div>
    // <div className="sc" ref={windowRef}>
    //   <p>{paragraphs}</p>
    //   <button className="scroll-to-top" onClick={scrollToTop}>
    //     Scroll to Top
    //   </button>
    // </div>
  );
}
