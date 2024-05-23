import { useState } from 'react';
import { StyleSheet, TextInput, View } from 'react-native';
import { Dropdown } from 'react-native-element-dropdown';

export function InputText(props) {
  return (
    <TextInput
      style={[
        styles.input,
        props.styles
      ]}
      {...props}
    />
  );
}

export function InputSelect(props) {
  
  const [value, setValue] = useState(null);

  return(
    <Dropdown
      style={styles.input}
      placeholderStyle={styles.placeholderStyle}
      selectedTextStyle={styles.selectedTextStyle}
      inputSearchStyle={styles.inputSearchStyle}
      search
      maxHeight={300}
      labelField="descricao"
      valueField="codigo"
      searchPlaceholder="Procurar"
      value={value}
      onChange={item => {
        setValue(item.value);
      }}
      {...props}
    />
  );
}

const styles = StyleSheet.create({
  input: {
    borderRadius: 8,
    paddingHorizontal: 8,
    color:'#fff',
    borderWidth: 1,
    width: '80%',
    height: 50,
    borderColor: '#00c530ff',
    marginBottom: 16,
  },
  dropdown: {
    borderRadius: 8,
    paddingHorizontal: 8,
    color:'#fff',
    borderWidth: 1,
    width: '80%',
    height: 50,
    borderColor: '#00c530ff',
    marginBottom: 16,
  },
  icon: {
    marginRight: 5,
  },
  placeholderStyle: {
    color: '#727272',
    fontSize: 16,
  },
  selectedTextStyle: {
    color:'#fff',
    fontSize: 16,
  },
  iconStyle: {
    width: 20,
    height: 20,
  },
  inputSearchStyle: {
    height: 40,
    fontSize: 16,
  },
});