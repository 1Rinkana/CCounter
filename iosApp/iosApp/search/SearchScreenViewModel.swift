import Foundation
import shared

extension SearchScreen {
    @MainActor class SearchScreenViewModel: ObservableObject {
        private let getProductsListUseCase = GetProductsListUseCase.init()
        
        @Published private(set) var isLoading: Bool = false
        @Published var request = "pizza"
        @Published private(set) var products:[ProductItem] = []
        @Published private(set) var loadFinished: Bool = false
        
        
        func loadProducts() async {
            if isLoading {
                return
            }
            
            isLoading = true
            
            do {
                let products = try await getProductsListUseCase.invoke(productsName: request)
                try await Task.sleep(until: .now + .seconds(1))
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
