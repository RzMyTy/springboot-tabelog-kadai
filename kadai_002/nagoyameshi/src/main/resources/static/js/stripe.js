const stripe = Stripe('pk_test_51OHmQgIhYmnFrNDSi0pWyxLAus05ZVPkyHV1Bo5rD8rkLsvEwBgzoCvO4nR457Qvcw6sxCH57Xmlbw8DKfig0WnT00QMANXGqX');

const paymentButton = document.querySelector('#paymentButton');
const elements = stripe.elements();
const cardElement = elements.create('card');
cardElement.mount('#card-element');

paymentButton.addEventListener('click', () => {
   stripe.redirectToCheckout({
     sessionId: sessionId
   })
 });