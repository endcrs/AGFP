import React from 'react';
import { Text, StyleSheet, TouchableOpacity } from 'react-native';

import { AntDesign } from '@expo/vector-icons';

export function Button({ title, style, ...rest }) {
  return (
    <TouchableOpacity {...rest} style={[styles.button, style]}>
      <Text style={styles.text}>{title}</Text>
    </TouchableOpacity>
  );
}

export function ButtonPlus({style, ...rest}){
  return (
    <TouchableOpacity {...rest} style={[styles.buttonPlus, style]} >
      <AntDesign name="pluscircle" size={50} color="#00C530" />
    </TouchableOpacity>
  )
}

const styles = StyleSheet.create({
  button: {
    backgroundColor: '#008420ff',
    borderRadius: 8,
    padding: 20,
  },
  text: {
    fontWeight: 'bold',
    color: '#FFF',
    fontSize: 16,
    textAlign: 'center'
  },
  buttonPlus:{
    marginLeft:'auto', 
    //marginRight:10,
  }
});