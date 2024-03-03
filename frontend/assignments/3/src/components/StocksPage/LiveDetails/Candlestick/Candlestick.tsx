export const Candlestick = ({
  percentChange,
  maxPercentChange,
}: {
  percentChange: number;
  maxPercentChange: number;
}) => {
  const maxHeight = 400;
  const scalingFactor = maxHeight / maxPercentChange;

  const height = Math.min(Math.abs(percentChange) * scalingFactor, maxHeight);

  const candlestickStyles = {
    height: `${height}px`,
    width: "15px",
    minWidth: "15px",
    border: percentChange >= 0 ? "1px solid green" : "1px solid red",
    backgroundColor: percentChange >= 0 ? "#b2f2bb" : "#ffc9c9",
    marginLeft: "1px",
    marginTop: "auto",
  };

  return <div style={candlestickStyles}></div>;
};
