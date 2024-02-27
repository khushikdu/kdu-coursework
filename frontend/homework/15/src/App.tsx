import "./App.css";
import { Body } from "../src/components/Body";
import { Header } from "../src/components/Header";
import { Provider } from "react-redux";
import { store } from "./redux/store";
import { PersistGate } from "redux-persist/integration/react";
import { persistStore } from "redux-persist";

const persistor = persistStore(store);

export default function App() {
  return (
    <Provider store={store}>
      <PersistGate persistor={persistor}>
        <div className="App">
          <Header />
          <Body />
        </div>
      </PersistGate>
    </Provider>
  );
}
