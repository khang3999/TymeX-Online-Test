# I. Introduction

Greeting,  
This is an introduction about the ideas and the challenges I encountered during the implementation of the test sections, including:  
**1. Folder CurrencyConverter**: This folder contains the source code for **Challenge 1 - Basic App Mobile Application Development**  
**2. Folder CodingSkillsAssessment**: This folder contains the source code for **Challenge 2 - Coding Skills Assessment**, which includes two projects: 
 - **2.1. Folder ProductInventoryManagement** (Question 2.1)
 - **2.2. Folder  ArrayManipulation** (Question 2.2)
 
I hope this introduction will provide you with an overview of the project structure.


# II. Challenge 1 - Basic App Mobile Application Development

## Intro
**Technical**  
- Android: **Kotlin**

**API**  
- I use [Open Exchange Rates](https://openexchangerates.org/) for my project. 

**UI**  
-   An input field for entering the amount.
-   A view to display the result after conversion.
-   Two combo boxes to select currency units.
-   A view to display the exchange rate.
-   A view to show the country name of the currency.
-   A view to record the time of the exchange rate.


## Implement  

After reading the documentation, I have the following idea:

 - Fetch data for the two combo boxes using the API: [https://openexchangerates.org/api/currencies.json?show_alternative=1](https://openexchangerates.org/api/currencies.json?show_alternative=1)
 - Fetch exchange rate data using the API: https://openexchangerates.org/api/latest.json?app_id=your-id-key (My API key = **064c243112a24b44ae648e5cceb060c3**)
 Since the API only provides one base currency, which is USD, I will convert to USD first. From there, I will convert to the target currency using the following algorithm:

Since the API only provides one base currency, which is USD, I will convert to USD first. From there, I will convert to the target currency using the following algorithm:

- Converted Amount = **Amount** × **( Exchange rate of target currency to USD / Exchange rate of source currency to USD )**  

Additionally, the exchange rates for currency from and currency to are calculated as follows:  

- Exchange Rate = **( Exchange rate of target currency to USD / Exchange rate of source currency to USD )**  


# III. Challenge 2 - The coding skills assessment  


## Question 2.1. Product Inventory Management  
**Product List:**  
Laptop: price 999.99, quantity 5  
Smartphone: price 499.99, quantity 10  
Tablet: price 299.99, quantity 0  
Smartwatch: price 199.99, quantity 3  

**Result:**  
Passed all unit test case

## Question 2.2. Array Manipulation and Missing Number Problem
Implement with
**Input array: [3, 7, 1, 2, 6, 4] (n = 6)**
**Result**  
Passed all unit test case:  
[1, 2, 3, 4, 5, 6, 7]  
[]  
[1]  
[-3, -2, -5, -4, -1]  
[ 0, -2, 2, 1]  
[-1, 3, 2, 1]  
# IV. Summary  
This is a complete description of my project. I have applied all the knowledge I have learned to complete this test to the best of my ability. If there are any areas that are not appropriate, please let me know immediately. I look forward to your feedback so that I can improve further. I would greatly appreciate that.  
Thank you very much.
