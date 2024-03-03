import { BrowserRouter, Routes, Route } from "react-router-dom";
// import { PersistGate } from "redux-persist/integration/react";
import { persistStore } from "redux-persist";
import { store } from "./redux/store";
import { Dashboard } from './components/Dasboard/DashBoard';
import StockPage from './components/StocksPage/StockPage.tsx'
import { Portfolio } from "./components/Portfolio/Portfolio.tsx";

const persistor = persistStore(store);

function App() {
  return (
    <div>
      {/* <PersistGate persistor={persistor}> */}
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/stock/:symbol" element={<StockPage />} />
            <Route path="/portfolio" element={<Portfolio />} />
          </Routes>
        </BrowserRouter>
      {/* </PersistGate> */}
    </div>
  );
}

export default App;
