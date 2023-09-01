var table = document.getElementById("data_table")

function debounce(func, timeout = 1000) {

    let timer;

    return (...args) => {
        clearTimeout(timer);
        timer = setTimeout(() => {
            func.apply(this, args);
        }, timeout);


        // remove DOM Saved table data
        var rowCount = table.rows.length;
        for (var i = rowCount - 1; i > 0; i--) {
            table.deleteRow(i);
        }

    };
}

function deleteEmployee(id) {
    console.log(id)
}

function saveInput() {

    let name = document.getElementById("search_input").value;

    fetch('test', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: name
        })
    }).then(response => response.json()).then(res => {
        if (document.getElementById("search_input").value == "") {
            data = "";
            function createTableFromObjects(data) {

                table.style.border = "1px solid #000";
                table.style.td = "1px solid #000";

                // Create table header row
                const keys = Object.keys(data[0]);
                keys.push("delete", "update")

                // Create table data rows
                for (var i = 0; i < data.length; i++) {

                    let deleteBtn = document.createElement('button');
                    deleteBtn.innerText = "delete"
                    deleteBtn.setAttribute("id", "dbtn");
                    deleteBtn.setAttribute("onclick", `deleteEmployee(${data[i]["id"]})`)

                    let editBtn = document.createElement('button');
                    editBtn.innerText = "edit"
                    editBtn.setAttribute("id", "ebtn");

                    const dataRow = document.createElement('tr');

                    for (const key of keys) {

                        const dataCell = document.createElement('td');

                        dataCell.style.border = "1px solid #000";

                        dataCell.textContent = data[i][key];

                        console.log(dataCell)

                        dataRow.appendChild(dataCell);
                    }

                    dataRow.insertCell(6).appendChild(deleteBtn);
                    dataRow.insertCell(7).appendChild(editBtn);
                    dataRow.deleteCell(9);
                    dataRow.deleteCell(8);
                    table.appendChild(dataRow);
                }

                return table;
            }

            // document.getElementById('table-container').firstElementChild.remove();

            const table1 = createTableFromObjects(res);

            tableContainer = document.getElementById('table-container');
            tableContainer.appendChild(table1);
            console.log(document.getElementById('table-container').firstElementChild)
        } else {
            function createTableFromObjects(data) {

                table.style.border = "1px solid #000";
                table.style.td = "1px solid #000";

                // Create table header row
                const keys = Object.keys(data[0]);
                keys.push("delete", "update")

                // Create table data rows
                for (var i = 0; i < data.length; i++) {

                    let deleteBtn = document.createElement('button');
                    deleteBtn.innerText = "delete"
                    deleteBtn.setAttribute("id", "dbtn");
                    deleteBtn.setAttribute("onclick", `deleteEmployee(${data[i]["id"]})`)

                    let editBtn = document.createElement('button');
                    editBtn.innerText = "edit"
                    editBtn.setAttribute("id", "ebtn");

                    const dataRow = document.createElement('tr');

                    for (const key of keys) {

                        const dataCell = document.createElement('td');

                        dataCell.style.border = "1px solid #000";

                        dataCell.textContent = data[i][key];

                        console.log(dataCell)

                        dataRow.appendChild(dataCell);
                    }

                    dataRow.insertCell(6).appendChild(deleteBtn);
                    dataRow.insertCell(7).appendChild(editBtn);
                    dataRow.deleteCell(9);
                    dataRow.deleteCell(8);
                    table.appendChild(dataRow);
                }

                return table;
            }

            // document.getElementById('table-container').firstElementChild.remove();

            const table1 = createTableFromObjects(res);

            tableContainer = document.getElementById('table-container');
            tableContainer.appendChild(table1);
            console.log(document.getElementById('table-container').firstElementChild)
        }
    })

}

const processChange = debounce(() => saveInput());

