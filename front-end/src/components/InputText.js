import { StyleSheet, TextInput } from 'react-native';
import { Dropdown } from 'react-native-element-dropdown';
import { TextInputMask } from 'react-native-masked-text';

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

export function MaskedInput({ type, value, onChangeText, placeholder, options, style, ...props }) {
  return (
    <TextInputMask
      type={type}
      value={value}
      onChangeText={onChangeText}
      placeholder={placeholder}
      options={options}
      style={[styles.input, style]}
      {...props}
    />
  );
};




export function InputSelect(props) {

  return(
    <Dropdown
      style={styles.input}
      placeholderStyle={styles.placeholderStyle}
      selectedTextStyle={styles.selectedTextStyle}
      inputSearchStyle={styles.inputSearchStyle}
      search
      maxHeight={300}
      searchPlaceholder="Procurar"
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