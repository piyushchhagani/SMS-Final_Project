import { StyleSheet, Text, View } from "react-native";
import React from "react";
import { Stack } from "expo-router";
import { UserProvider } from "../context/UserContext";
import { Provider } from "react-redux";
import { smsStore } from "../store/store";

const _layout = () => {
  return (
    <Provider store={smsStore}>
      <UserProvider>
        <Stack></Stack>
      </UserProvider>
    </Provider>
  );
};

export default _layout;

const styles = StyleSheet.create({
  rootLayout: {
    flex: 1,
    alignContent: "center",
    alignItems: "center",
  },
  myText: {
    fontWeight: "600",
    fontSize: 40,
  },
});
