<h1>final-bank-project</h1>
<hr>
<h2>API</h2>

<h4>Balance</h4>
<li>GET <i>/balance/getBalance/{userId}</i></li>
<li>PUT <i>/balance/putMoney/{userId}</i><br />
BODY {"sum": 1000}</li>
<li>PUT <i>/balance/takeMoney/{userId}</i><br />
BODY {"sum": 1000}</li>

<h4>Transactions</h4>
<li>GET <i>/transactions/getOperationList/2?startDate=2022-05-26&endDate=2022-05-27</i></li>