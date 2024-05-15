const formatCPF = (text) => {
    const cleanedText = text.replace(/\D/g, '');

    let formattedCPF = cleanedText;

    if (cleanedText.length > 3) {
        formattedCPF = `${cleanedText.slice(0, 3)}.${cleanedText.slice(3)}`;
    }

    if (cleanedText.length > 6) {
        formattedCPF = `${formattedCPF.slice(0, 7)}.${formattedCPF.slice(7)}`;
    }

    if (cleanedText.length > 9) {
        formattedCPF = `${formattedCPF.slice(0, 11)}-${formattedCPF.slice(11)}`;
    }

    return formattedCPF;
}

export { formatCPF };