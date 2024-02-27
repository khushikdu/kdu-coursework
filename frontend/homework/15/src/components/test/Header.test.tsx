import { fireEvent, render, screen } from "@testing-library/react";
import { Body } from "../Body";
import { Provider } from "react-redux";
import { Header } from "../Header";
import { store } from "../../redux/store";
import "@testing-library/jest-dom";

test("searches items to the list", () => {
  render(
    <Provider store={store}>
      <Header />
      <Body />
    </Provider>
  );

  // checking the functionality of add button
  const inputField = screen.getByTestId("input-field");
  const addBtnField = screen.getByTestId("add-btn");
  fireEvent.change(inputField, { target: { value: "Adding item 1" } });
  fireEvent.click(addBtnField);
  fireEvent.change(inputField, { target: { value: "Adding item 2" } });
  fireEvent.click(addBtnField);

  const addedItem = screen.getByText("Adding item 1");
  expect(addedItem).toBeInTheDocument();

  const searchInput = screen.getByPlaceholderText("Search") as HTMLInputElement;
  fireEvent.change(searchInput, { target: { value: "Adding item 1" } });

  const searchedItem = screen.getByText("Adding item 1");
  expect(searchedItem).toBeInTheDocument();
});
