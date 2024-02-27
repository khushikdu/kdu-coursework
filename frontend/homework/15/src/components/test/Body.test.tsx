import { fireEvent, render, screen } from "@testing-library/react";
import { Body } from "../Body";
import { Provider } from "react-redux";
import { store } from "../../redux/store";
import "@testing-library/jest-dom";

test("deletes items to the list", () => {
  render(
    <Provider store={store}>
      <Body />
    </Provider>
  ); 

  // checking the functionality of add button
  const inputField = screen.getByTestId("input-field");
  const addBtnField = screen.getByTestId("add-btn");
  fireEvent.change(inputField, { target: { value: "Adding item" } });
  fireEvent.click(addBtnField);

  const addedItem = screen.getByText("Adding item");
  expect(addedItem).toBeInTheDocument();

  //checking the functionality of delete button
  const deletebtnField = screen.getByTestId("delete-btn");
  fireEvent.click(deletebtnField);

  expect(addedItem).not.toBeInTheDocument();
});

test("clear items from the list", () => {
  render(
    <Provider store={store}>
      <Body />
    </Provider>
  );

  const inputField = screen.getByTestId("input-field");
  const addBtnField = screen.getByTestId("add-btn");

  //checking the functionality of clear button
  const clearBtnfield = screen.getByTestId("clear-btn");
  fireEvent.change(inputField, { target: { value: "Adding item 1" } });
  fireEvent.click(addBtnField);
  const addedItem1 = screen.getByText("Adding item 1");
  fireEvent.change(inputField, { target: { value: "Adding item 2" } });
  fireEvent.click(addBtnField);
  const addedItem2 = screen.getByText("Adding item 2");
  fireEvent.click(clearBtnfield);
  expect(addedItem1).not.toBeInTheDocument();
  expect(addedItem2).not.toBeInTheDocument();
});

test("add items to the list", () => {
  render(
    <Provider store={store}>
      <Body />
    </Provider>
  );

  // checking the functionality of add button
  const inputField = screen.getByTestId("input-field");
  const addBtnField = screen.getByTestId("add-btn");
  fireEvent.change(inputField, { target: { value: "Adding item" } });
  fireEvent.click(addBtnField);

  const addedItem = screen.getByText("Adding item");
  expect(addedItem).toBeInTheDocument();
});
