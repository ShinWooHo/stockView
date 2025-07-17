
    document.addEventListener("DOMContentLoaded", function () {
    const searchForm = document.querySelector("form");
    const resultContainer = document.querySelector("#company-table-body");

    searchForm.addEventListener("submit", function (e) {
        e.preventDefault(); // form의 기본 제출을 막는 용도

        const formData = new FormData(searchForm);
        const params = new URLSearchParams(formData).toString();

        axios.get(`/stock/search?${params}`)
            .then(response => {
                console.log("응답 데이터", response.data);

                // 결과 초기화
                resultContainer.innerHTML = "";

                // 데이터가 있는 경우 출력
                if (response.data && response.data.length > 0) {
                    response.data.slice(0, 10).forEach(company => {
                        const row = document.createElement("tr");

                        row.innerHTML = `
                            <td>${company.comp_name}</td>
                            <td>${company.industry}</td>
                            <td>${company.main_prod}</td>
                            <td>${company.list_date?.split("T")[0] ?? ""}</td>
                            <td>${company.fisc_month}</td>
                            <td>${company.ceo_name}</td>
                            <td><a href="${company.website}" target="_blank">홈페이지</a></td>
                            <td>${company.region}</td>
                        `;
                        resultContainer.appendChild(row);
                    });
                } else {
                    resultContainer.innerHTML = `
                        <tr><td colspan="8" style="text-align:center;">검색 결과가 없습니다.</td></tr>
                `}

            })
            .catch(error => {
                console.error("검색 실패", error);
                resultContainer.innerHTML = `
                        <tr><td colspan="8" style="text-align:center;">검색 중 오류가 발생했습니다.</td></tr>
                    `;
            });
    });
});