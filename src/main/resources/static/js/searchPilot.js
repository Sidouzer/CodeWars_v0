function search(event) {
    const name = document.getElementById("search").value;
    document.querySelectorAll("tbody tr").forEach(row => {
        const firstname = row.querySelector("td:nth-of-type(2)").innerText.toLowerCase();
        const lastname = row.querySelector("td:nth-of-type(3)").innerText.toLowerCase();
        if(firstname.includes(name) || lastname.includes(name)){
            row.style.display = "";
        }
        else {
            row.style.display = "none";
        }
    })
}