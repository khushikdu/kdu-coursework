import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Dashboard } from './components/Dasboard/DashBoard';
import StockPage from './components/StocksPage/StockPage.tsx'
import { Portfolio } from "./components/Portfolio/Portfolio.tsx";

function App() {
  return (
    <div>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/stock/:symbol" element={<StockPage />} />
            <Route path="/portfolio" element={<Portfolio />} />
          </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;

