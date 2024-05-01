import React from 'react';
import { Text, StyleSheet, TouchableOpacity } from 'react-native';

export function Button({ title, style, ...rest }) {
  return (
    <TouchableOpacity {...rest} style={[styles.button, style]}>
      <Text style={styles.text}>{title}</Text>
    </TouchableOpacity>
  );
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
});