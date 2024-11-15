const formatValue = (value) => {

    if (!value) return 'R$ 0,00';
    const valueToFormat = parseFloat(value).toFixed(2);
    return 'R$ ' + valueToFormat
    .replace(/\D/g, '')
    .replace(/(\d)(\d{8})$/, '$1.$2')
    .replace(/(\d)(\d{5})$/, '$1.$2')
    .replace(/(\d)(\d{2})$/, '$1,$2');
};

const formatValueToAPI = (value) => {
    const valueToApi = parseFloat(
        value
            .replace('R$', '') // Remove o símbolo "R$"
            .replace(/\s/g, '') // Remove espaços extras
            .replace(/\./g, '') // Remove separadores de milhar
            .replace(',', '.') // Substitui vírgula decimal por ponto
    );

    return valueToApi;
}

export {formatValue, formatValueToAPI}