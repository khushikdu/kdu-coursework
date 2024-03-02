import React, { useState, createContext, useMemo } from "react";
import "./App.css";
import { Body } from "./Todo-useState/Body";
import { Header } from "./Todo-useState/Header";

//creating interface for individual list items
interface IListItem {
  id: number;
  todo: string;
}

interface ListContextType {
  list: IListItem[];
  handleSearch: (query: string) => void;
  searchQuery: string;
  addItem: (text: string) => void;
  deleteItem: (id: number) => void;
}

//create a context for listItem
export const ListItemContext = createContext<ListContextType>({
  list: [],
  handleSearch: () => {},
  searchQuery: "",
  addItem: () => {},
  deleteItem: () => {},
});

//provider
interface ListContextAPI {
  children: React.ReactNode;
}

export const ProviderUsage = ({ children }: ListContextAPI) => {
  const [listItem, setListItem] = useState<IListItem[]>([]);
  const [searchQuery, setSearchQuery] = useState<string>("");

  const handleSearch = (query: string) => {
    setSearchQuery(query);
  };

  const addItem = (text: string) => {
    const newItem: IListItem = {
      id: listItem.length + 1,
      todo: text,
    };
    setListItem([...listItem, newItem]);
  };

  const deleteItem = (id: number) => {
    setListItem(listItem.filter((item) => item.id !== id));
  };

  const ContextProviderType = useMemo(
    () => ({
      list: listItem,
      searchQuery: searchQuery,
      handleSearch: handleSearch,
      addItem: addItem,
      deleteItem: deleteItem,
    }),
    [listItem, searchQuery]
  );

  return (
    <ListItemContext.Provider value={ContextProviderType}>
      {children}
    </ListItemContext.Provider>
  );
};

export function App() {
  return (
    <ProviderUsage>
      <Header />
      <Body />
    </ProviderUsage>
  );
}
