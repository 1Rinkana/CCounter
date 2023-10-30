import Foundation
import shared

extension SearchScreen {
    @MainActor class SearchScreenViewModel: ObservableObject {
        private let getProductsListUseCase = GetProductsListUseCase.init()
        
        @Published private(set) var isLoading: Bool = false
        @Published var request = ""
        @Published private(set) var products:[ProductItem] = []
        @Published private(set) var loadFinished: Bool = false
        
        
        func loadProducts() async {
            if isLoading {
                return
            }
            
            do {
                let products = try await getProductsListUseCase.invoke(productsName: request)
                isLoading = false
                loadFinished = products.isEmpty
                
                self.products = products
            } catch {
                isLoading = false
                loadFinished = true
                
                print(error.localizedDescription)
            }
        }
    }
}
