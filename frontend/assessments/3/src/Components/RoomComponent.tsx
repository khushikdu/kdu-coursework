import { AppDispatch } from "../redux/store";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useEffect, useState } from "react";
import { getRooms } from "../redux/thunk/getRooms";
import { AddOn, RoomType } from "../Interface/RoomDetail";
import { styles } from "./Room.style";

export function RoomComponent() {
  const dispatch: AppDispatch = useDispatch();
  const apiData: RoomType[] = useSelector(
    (state: RootState) => state.room.roomTypes
  );

  console.log("hereee", apiData);
  const [total, setTotal] = useState<number>(0);

  const [addOn, setAddon] = useState<boolean>(false);

  let data2: string[] = [];

  let addon: AddOn[] = [];
  let costAddon: number[] = [];
  const displayAddOns = (data: RoomType) => {
    setTotal(parseInt(data.costPerNight) * 1.18);
    // setTotal(parseInt(data.addOns[2].cost))
    addon = data.addOns;
    data2 = addon.map((item) => item.name);
    costAddon = addon.map((item) => parseInt(item.cost));
    console.log(data2);
    setAddon(true);
    setData1([...data2]);
    console.log(costAddon);
    console.log(data1, typeof data1);
  };

  const [data1, setData1] = useState<string[]>([]);

  console.log("data1", data1);

  useEffect(() => {
    dispatch(getRooms());
  }, [dispatch]);

  return (
    <div className="main">
      Hotel Booking
      <div className="select" style={styles.select}>
        Select Room Type
      </div>
      <div className="roomSelection" style={styles.roomSelection}>
        {apiData.map((data) => (
          <div className="indivData" key={data.id} style={styles.indivData}>
            <button
              className="dataBtn"
              style={styles.dataBtn}
              onClick={() => displayAddOns(data)}
            >
              {data.name}
            </button>
          </div>
        ))}
      </div>
      <div className="dates" style={styles.dates}>

      <div className="toDate">
        To:
        <input type="date" />
      </div>
      <div className="FromDate">
        From:
        <input type="date" />
      </div>

      </div>
      <div className="select" style={styles.select}>
        Select Add Ons
      </div>
      <div>
        <div className="roomSelection" style={styles.roomSelection}>
          {data1.map((additems) => (
            <div key={additems} className="indivData" style={styles.indivData}>
              <button
                className="dataBtn"
                style={styles.dataBtn}
                // onClick={}
              >
                {additems}
              </button>
            </div>
          ))}
        </div>
      </div>
      Total Price : {total}
      <br />
      <div className="submit">
      <button
                className="dataBtn"
                style={styles.dataBtn}
              >
                Submit
              </button>
      </div>
    </div>
  );
}
