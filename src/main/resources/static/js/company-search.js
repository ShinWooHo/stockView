document.addEventListener("DOMContentLoaded", function () {
    const searchForm = document.querySelector("form");
    const resultContainer = document.querySelector("#company-table-body");
    const pagination = document.getElementById("pagination");

    let currentPage = 1;
    const pageSize = 10;

    searchForm.addEventListener("submit", function (e) {
        e.preventDefault();
        currentPage = 1; // 검색 시 1페이지 고정
        fetchData();

    });

    function fetchData() {
        const formData = new FormData(searchForm);
        const params = new URLSearchParams(formData); // 쿼리 스트링으로 변환
        params.append("page", currentPage);
        params.append("size", pageSize);

        // URL로 axios 비동기 GET 요청
        axios.get(`/stock/search?${params.toString()}`)
            .then(response => {
                console.log("응답 데이터", response.data);

                const data = response.data;
                const items = data.items || [];
                const totalPages = data.totalPages || 0;

                // 기존 테이블 내용 비움
                resultContainer.innerHTML = "";

                if (items.length > 0) {
                    items.forEach(company => {
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
                        <tr><td colspan="8" style="text-align: center;">검색 결과가 없습니다.</td></tr>
                    `;
                }
                renderPagination(totalPages);
            })
            .catch(error => {
                console.error("검색 실패", error);
                resultContainer.innerHTML = `
                    <tr><td colspan="8" style="text-align: center;">검색 중 오류가 발생했습니다.</td></tr>
                `;
            });
    }

    function renderPagination(totalPages) {
        pagination.innerHTML = "";

        const pageGroupSize = 10;
        const currentGroup = Math.floor((currentPage - 1) / pageGroupSize);
        const startPage = currentGroup * pageGroupSize + 1;
        const endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        // 이전 버튼
        const prevBtn = document.createElement("button");
        prevBtn.innerHTML = `&#8592; 이전`;
        prevBtn.className = "page-btn";
        prevBtn.disabled = startPage === 1;
        if (prevBtn.disabled) prevBtn.classList.add("disabled");
        prevBtn.addEventListener("click", () => {
            currentPage = startPage - 1;
            fetchData();
        });
        pagination.appendChild(prevBtn);

        // 숫자 버튼
        for (let i = startPage; i <= endPage; i++) {
            const btn = document.createElement("button");
            btn.textContent = i;
            btn.className = "page-btn";
            if (i === currentPage) btn.classList.add("active");

            btn.disabled = i === currentPage;
            btn.addEventListener("click", () => {
                currentPage = i;
                fetchData();
            });
            pagination.appendChild(btn);
        }

        // 다음 버튼
        const nextBtn = document.createElement("button");
        nextBtn.innerHTML = `다음 &#8594`;
        nextBtn.className = "page-btn";
        nextBtn.disabled = endPage >= totalPages;
        if(nextBtn.disabled) nextBtn.classList.add("disabled");
        nextBtn.addEventListener("click", () => {
            currentPage = endPage + 1;
            fetchData();
        });
        pagination.appendChild(nextBtn);
    }

    fetchData();
});