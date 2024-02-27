import { Provider } from "react-redux";
import { store } from "../../src/redux/store";
import { Body } from "../../src/components/Body";
import { Header } from "../../src/components/Header";

describe("ComponentName.cy.tsx", () => {
  it("tests component", () => {
    cy.mount(
      <Provider store={store}>
        <Header />
      </Provider>
    );
    
    cy.mount(
      <Provider store={store}>
        <Body />
      </Provider>
    );  
  });
});
