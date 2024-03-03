import ShowChartIcon from "@mui/icons-material/ShowChart";
import { styles } from "./Header.styles";
import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { useEffect } from "react";
import { stockthunk } from "../../redux/thunk/stockThunk";

function Header() {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(stockthunk());
  }, [dispatch]);

  return (
    <div className="main" style={styles.main}>
      <div className="main" style={styles.mainHeader}>
        <div className="left-logo-title" style={styles.leftLogoTitle}>
          <Link to={`/`} style={styles.linkStyle}>
            <ShowChartIcon />
          </Link>
          KDU Stock Market
        </div>

        <div className="right-title" style={styles.rigthTitle}>
          <div className="summariser" style={styles.summariser}>
            Summarizer
          </div>
          <div className="portfolio">
            <Link to={`/portfolio`} style={styles.linkStyle}>
              My Portfolio
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Header;
