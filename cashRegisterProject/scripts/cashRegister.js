function checkCashRegister(price, cash, cid) {
    let cashBack = cash - price;
    let denominationLookup = {
      "PENNY": 0.01,
      "NICKEL": 0.05,
      "DIME": 0.10,
      "QUARTER": 0.25,
      "ONE": 1,
      "FIVE": 5,
      "TEN": 10,
      "TWENTY": 20,
      "ONE HUNDRED": 100
    };
    let amountInDrawer = cid.reduce((runningTotal, denominationInDrawer) => {
      return runningTotal + denominationInDrawer[1];
    }, 0);
    if (amountInDrawer - cashBack == 0) {
      return {
        status: "CLOSED",
        change: cid
      };
    }
    let change = [];
    for (let i = cid.length - 1; i >= 0; i--) {
      let denomination = cid[i][0];
      let denominationInDrawer = cid[i][1]
      let denominationValue = denominationLookup[denomination];
      let denominationCount = Math.floor(cashBack / denominationValue);
      let amountInDenomination = denominationCount * denominationValue;
      if (denominationCount >= 1 && amountInDenomination < denominationInDrawer) {
        cid[i][1] -= amountInDenomination;
        cashBack = parseFloat(Number.parseFloat(cashBack - amountInDenomination).toFixed(2));
        change.push([denomination, amountInDenomination]);
        continue;
      }
      if (denominationCount >= 1 && amountInDenomination >= denominationInDrawer) {
        change.push([denomination, denominationInDrawer]);
        cashBack = parseFloat(Number.parseFloat(cashBack - denominationInDrawer).toFixed(2));
        cid[i][1] = 0;
        continue;
      }
      if (cashBack == 0) {
        break;
      }
    }
  
    if (cashBack !== 0.00) {
      return {
        status: "INSUFFICIENT_FUNDS",
        change: []
      };
    }
  
    return {
      status: "OPEN",
      change: change
    }
  }
  
  console.log(checkCashRegister(3.26, 100, [["PENNY", 1.01], ["NICKEL", 2.05], ["DIME", 3.1], ["QUARTER", 4.25], ["ONE", 90], ["FIVE", 55], ["TEN", 20], ["TWENTY", 60], ["ONE HUNDRED", 100]]));