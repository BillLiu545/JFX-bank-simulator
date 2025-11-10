# JFX-bank-simulator
This repository contains a simple project that uses JavaFX to simulate a bank and its operations. Functions include withdrawing from/depositing to accounts, and creating/removing accounts.

# How does it work?
The entire bank is simulated as a TreeMap, where all accounts can be searched with a given ID. A new ID is assigned for each newly created account in the bank. Withdraw and deposit require searching the ID to access that certain account.
