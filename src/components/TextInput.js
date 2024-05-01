import { StyleSheet, TextInput } from 'react-native';

export function CaixaDeTexto(props) {
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
});