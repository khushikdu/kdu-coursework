import { render, screen } from "@testing-library/react";
import { Provider } from "react-redux";
import { Body } from "../Body";
import { Header } from "../Header";
import { store } from "../../redux/store";
import "@testing-library/jest-dom";

describe("Render the list section", () => {
  beforeEach(() => {
    render(
      <Provider store={store}>
        <Header />
        <Body />
      </Provider>
    );
  });

 
  test("Searches for Heading", () => {
    expect(screen.getByText("Item Lister")).toBeInTheDocument();
  });

  test("Searches for Input Filed", () => {
    expect(screen.getByTestId("input-field")).toBeInTheDocument();
  });

  test("Searches for Add Button", () => {
    expect(screen.getByTestId("add-btn")).toBeInTheDocument();
  });

  test("Searches for Clear Button", () => {
    expect(screen.getByTestId("clear-btn")).toBeInTheDocument();
  });

});
