const formatValue = (value) => {

    if (!value) return 'R$ 0,00';
    const valueToFormat = parseFloat(value).toFixed(2);
    return 'R$ ' + valueToFormat
    .replace(/\D/g, '')
    .replace(/(\d)(\d{8})$/, '$1.$2')
    .replace(/(\d)(\d{5})$/, '$1.$2')
    .replace(/(\d)(\d{2})$/, '$1,$2');
};

export {formatValue}