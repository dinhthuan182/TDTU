import React, { useState, useEffect } from 'react';
import { StyleSheet, Text, View, TextInput, TouchableOpacity } from 'react-native';

const ConversionTypeButton = props => {
  const backgroundColor =
    props.fromCurrency === props.from && props.toCurrency === props.to
      ? 'lightblue'
      : null;
  const buttonStyle = { backgroundColor: backgroundColor };

  const fromFlag = props.from === 'usd' ? 'USD 🇺🇸' : 'VND 🇻🇳';
  const toFlag = props.to === 'usd' ? '🇺🇸 USD' : '🇻🇳 VND';

  return (
    <TouchableOpacity
      style={[styles.button, buttonStyle]}
      onPress={() => props.setConversionCurrencies(props.from, props.to)}
    >
      <Text style={styles.buttonText}>
        {fromFlag} to {toFlag}
      </Text>
    </TouchableOpacity>
  );
};
const ConversionTypeView = props => {
  return (
    <View style= {styles.flexView}>
      <Text>Current currency:</Text>
      <Text style={styles.currencyText}>{props.currentCurrencyValue}</Text>
      <Text>Conversion currenecy:</Text>
      <Text style={styles.currencyText}>0.00</Text>
    </View>
  );
}
const FormattedCurrency = props => {
  const format = props.type === 'usd' ? 'us' : 'vn';
  const currency = props.type === 'usd' ? 'USD' : 'VND';
  const flag = props.type === 'usd' ? '🇺🇸' : '🇻🇳';

  const formatter = new Intl.NumberFormat(format, {
    currency,
    style: 'currency'
  });

  return (
    <Text style={styles.currencyText}>
      {formatter.format(props.value)} {flag} {currency}
    </Text>
  );
};



export default function App() {
  const [currentCurrencyValue, setFromCurrencyValue] = useState(0);
  const [convertedCurrencyValue, setConvertedValue] = useState(0);
  const [toCurrency, setToCurrency] = useState('usd');
  const [fromCurrency, setFromCurrency] = useState('vnd');

  const convertCurrency = () => {
    let value;
    if (fromCurrency === 'vnd') {
      value = currentCurrencyValue / 23000;
    } else {
      value = 23000 * currentCurrencyValue;
    }
    setConvertedValue(value);
  };
  useEffect(convertCurrency);
  const setConversionCurrencies = (from, to) => {
    setToCurrency(to);
    setFromCurrency(from);
  };

  return (
    <View style={styles.container}>
      <Text>Please enter the value of the currency you want to convert</Text>
      <TextInput 
      keyboardType = "number-pad"
      autoFocus
      textAlign="center"
      style = {styles.inputMount}
      placeholder="100,000,000 VND"
      onChangeText={setFromCurrencyValue}
      selectionColor="red"/>
      <ConversionTypeButton 
        to="usd" from="vnd"
        toCurrency={toCurrency}
        fromCurrency={fromCurrency}
        setConversionCurrencies={setConversionCurrencies}/>
      <ConversionTypeButton 
        to="vnd" from="usd"
        toCurrency={toCurrency}
        fromCurrency={fromCurrency}
        setConversionCurrencies={setConversionCurrencies}/>
      <View style= {styles.flexView}>
      <Text>Current currency:</Text>
      <FormattedCurrency
  type={fromCurrency}
  value={currentCurrencyValue}
/>
      <Text>Conversion currenecy:</Text>
      <FormattedCurrency
  type={toCurrency}
  value={convertedCurrencyValue}
/>
    </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    marginTop: 50,
    alignItems: 'center',
    justifyContent: 'flex-start'
  },
  inputMount: {
    height: 60,
    padding: 5,
    width: 300,
    fontSize: 35,
    borderWidth: 1,
    borderColor: 'lightblue'
  },
  button: {
    height: 35,
    width: 200,
    margin: 10,
    borderWidth: 2,
    borderRadius: 20,
    alignItems: 'center',
    borderColor: 'lightblue',
    justifyContent: 'center'
  },
  currencyText: {
    fontSize: 30,
    color: 'green',
    fontWeight: 'bold',
  },
  flexView: {
    justifyContent: 'center',
    alignItems: 'center'
  },
});
