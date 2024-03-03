import { fireEvent, render, screen } from "@testing-library/react";
import { Body } from "../Body";
import { Provider } from "react-redux";
import { Header } from "../Header";
import { store } from "../../redux/store";
import "@testing-library/jest-dom";

test("performs test for h1 tag", () => {
  render(
    <Provider store={store}>
      <Header />
      {/* <Body /> */}
    </Provider>
  );

  const itemLister = screen.getByText("Item Lister");
  expect(itemLister).toBeInTheDocument();

  const searchInput = screen.getByPlaceholderText("Search") as HTMLInputElement;
  fireEvent.change(searchInput, { target: { value: "Adding item 1" } });
});

test("performs test for search bar", () => {
  render(
    <Provider store={store}>
      <Header />
    </Provider>
  );

  const itemLister = screen.getByText("Item Lister");
  expect(itemLister).toBeInTheDocument();
});
