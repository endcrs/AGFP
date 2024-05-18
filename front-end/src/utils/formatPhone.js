const formatPhoneNumber = (text) => {
    // Remove tudo que não for dígito
    let cleaned = ('' + text).replace(/\D/g, '');

    if (cleaned.length > 11) {
        cleaned = cleaned.slice(0, 11);
    }

    let formatted = cleaned;
    if (cleaned.length > 6) {
        formatted = `(${cleaned.slice(0, 2)}) ${cleaned.slice(2, 7)}-${cleaned.slice(7, 11)}`;
    } else if (cleaned.length > 2) {
        formatted = `(${cleaned.slice(0, 2)}) ${cleaned.slice(2, 7)}`;
    } else if (cleaned.length > 0) {
        formatted = `(${cleaned.slice(0, 2)}`;
    }


    return formatted.trim();
};
export {formatPhoneNumber}

