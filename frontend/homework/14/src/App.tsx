import './App.css';
import { Body } from '../src/components/Body';
import { Header } from '../src/components/Header';
import { Provider } from 'react-redux';
import { store } from './redux/store';

export function App() { 
  return (
    <Provider store={store}>
      <div className="App">
      <Header />
      <Body      />
    </div>
    </Provider>
    
  );
}
