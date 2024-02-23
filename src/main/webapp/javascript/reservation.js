const reservationMessageContentElem = document.getElementById("reservation-message-content");
const errorReservationMessageContentElem = document.querySelector(".error-reservation-message-content");
const errorReservationMessageContent = "Votre message ne peut dépasser 250 caractères."

reservationMessageContentElem.addEventListener("focus", () => {
	reservationMessageContentElem.classList.add("textarea-style");
});

reservationMessageContentElem.addEventListener("blur", () => {
	reservationMessageContentElem.classList.remove("textarea-style");
})

reservationMessageContentElem.addEventListener("input", (e) => {
	if(e.target.value.length > 250) {
		errorReservationMessageContentElem.textContent = errorReservationMessageContent;
		reservationMessageContentElem.classList.add("error-border");
	} else {
		errorReservationMessageContentElem.textContent = "";
		reservationMessageContentElem.classList.remove("error-border");
	}
})